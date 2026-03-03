using NetArticle.Models;
using NetArticle.DTO;

namespace NetArticle.Repositories;

public interface IClientRepository
{
    Task<List<Client>> GetAllAsync();
    Task<Client?> GetByIdAsync(int id);
    Task<List<Client>> SearchByNameAsync(string name);
    Task<Client> CreateAsync(Client client);
    Task<Client?> UpdateAsync(int id, string name);
    Task<bool> DeleteAsync(int id);
    Task<bool> ExistsAsync(int id);
}