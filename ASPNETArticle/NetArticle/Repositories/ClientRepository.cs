using Microsoft.EntityFrameworkCore;
using NetArticle.Data;
using NetArticle.Models;

namespace NetArticle.Repositories;

public class ClientRepository : IClientRepository
{
    private readonly ApplicationDbContext _context;
    
    public ClientRepository(ApplicationDbContext context)
    {
        _context = context;
    }
    
    public async Task<List<Client>> GetAllAsync()
    {
        return await _context.Clients
            .OrderBy(c => c.Id)
            .ToListAsync();
    }
    
    public async Task<Client?> GetByIdAsync(int id)
    {
        return await _context.Clients.FindAsync(id);
    }
    
    public async Task<List<Client>> SearchByNameAsync(string name)
    {
        return await _context.Clients
            .Where(c => EF.Functions.ILike(c.Name, $"%{name}%"))
            .OrderBy(c => c.Id)
            .ToListAsync();
    }
    
    public async Task<Client> CreateAsync(Client client)
    {
        _context.Clients.Add(client);
        await _context.SaveChangesAsync();
        return client;
    }
    
    public async Task<Client?> UpdateAsync(int id, string name)
    {
        var client = await _context.Clients.FindAsync(id);
        if (client == null)
        {
            return null;
        }
        
        client.Name = name;
        await _context.SaveChangesAsync();
        return client;
    }
    
    public async Task<bool> DeleteAsync(int id)
    {
        var client = await _context.Clients.FindAsync(id);
        if (client == null)
        {
            return false;
        }
        
        _context.Clients.Remove(client);
        await _context.SaveChangesAsync();
        return true;
    }
    
    public async Task<bool> ExistsAsync(int id)
    {
        return await _context.Clients.AnyAsync(c => c.Id == id);
    }
}