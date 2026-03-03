using System.Text.Json.Serialization;

namespace NetArticle.DTO;

public class ClientResponse
{
    [JsonPropertyName("id")]
    public int Id { get; set; }
    
    [JsonPropertyName("name")]
    public string Name { get; set; } = string.Empty;
    
    public ClientResponse() { }
    
    public ClientResponse(int id, string name)
    {
        Id = id;
        Name = name;
    }
}

public class CreateClientRequest
{
    [JsonPropertyName("name")]
    public string Name { get; set; } = string.Empty;
}

public class UpdateClientRequest
{
    [JsonPropertyName("id")]
    public int Id { get; set; }
    
    [JsonPropertyName("name")]
    public string Name { get; set; } = string.Empty;
}

public class SearchClientRequest
{
    [JsonPropertyName("name")]
    public string Name { get; set; } = string.Empty;
}