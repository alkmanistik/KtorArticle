using NetArticle.DTO;

namespace NetArticle.Services;

public interface IClientService
{
    Task<List<ClientResponse>> GetAllClientsAsync();
    Task<ClientResponse?> GetClientByIdAsync(int id);
    Task<List<ClientResponse>> SearchClientsByNameAsync(string name);
    Task<ClientResponse> CreateClientAsync(CreateClientRequest request);
    Task<ClientResponse?> UpdateClientAsync(UpdateClientRequest request);
    Task<bool> DeleteClientAsync(int id);
}