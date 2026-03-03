using NetArticle.DTO;
using NetArticle.Models;
using NetArticle.Repositories;

namespace NetArticle.Services;

public class ClientService : IClientService
{
    private readonly IClientRepository _repository;
    
    public ClientService(IClientRepository repository)
    {
        _repository = repository;
    }
    
    public async Task<List<ClientResponse>> GetAllClientsAsync()
    {
        var clients = await _repository.GetAllAsync();
        return clients.Select(c => new ClientResponse(c.Id, c.Name)).ToList();
    }
    
    public async Task<ClientResponse?> GetClientByIdAsync(int id)
    {
        var client = await _repository.GetByIdAsync(id);
        if (client == null)
        {
            return null;
        }
        
        return new ClientResponse(client.Id, client.Name);
    }
    
    public async Task<List<ClientResponse>> SearchClientsByNameAsync(string name)
    {
        var clients = await _repository.SearchByNameAsync(name);
        return clients.Select(c => new ClientResponse(c.Id, c.Name)).ToList();
    }
    
    public async Task<ClientResponse> CreateClientAsync(CreateClientRequest request)
    {
        var client = new Client(request.Name);
        var created = await _repository.CreateAsync(client);
        return new ClientResponse(created.Id, created.Name);
    }
    
    public async Task<ClientResponse?> UpdateClientAsync(UpdateClientRequest request)
    {
        var updated = await _repository.UpdateAsync(request.Id, request.Name);
        if (updated == null)
        {
            return null;
        }
        
        return new ClientResponse(updated.Id, updated.Name);
    }
    
    public async Task<bool> DeleteClientAsync(int id)
    {
        return await _repository.DeleteAsync(id);
    }
}