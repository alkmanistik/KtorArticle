using Microsoft.AspNetCore.Mvc;
using NetArticle.DTO;
using NetArticle.Services;

namespace NetArticle.Controllers;

[ApiController]
[Route("[controller]")]
public class ClientController : ControllerBase
{
    private readonly IClientService _clientService;
    private readonly ILogger<ClientController> _logger;
    
    public ClientController(IClientService clientService, ILogger<ClientController> logger)
    {
        _clientService = clientService;
        _logger = logger;
    }
    
    [HttpPost]
    [ProducesResponseType(typeof(ClientResponse), StatusCodes.Status201Created)]
    [ProducesResponseType(StatusCodes.Status400BadRequest)]
    public async Task<ActionResult<ClientResponse>> CreateClient([FromBody] CreateClientRequest request)
    {
        try
        {
            if (string.IsNullOrWhiteSpace(request.Name))
            {
                return BadRequest("Имя клиента не может быть пустым");
            }
            
            var client = await _clientService.CreateClientAsync(request);
            return CreatedAtAction(nameof(GetClientById), new { id = client.Id }, client);
        }
        catch (Exception ex)
        {
            _logger.LogError(ex, "Ошибка при создании клиента");
            return StatusCode(500, "Внутренняя ошибка сервера");
        }
    }
    
    [HttpGet("all")]
    [ProducesResponseType(typeof(List<ClientResponse>), StatusCodes.Status200OK)]
    public async Task<ActionResult<List<ClientResponse>>> GetAllClients()
    {
        var clients = await _clientService.GetAllClientsAsync();
        return Ok(clients);
    }
    
    [HttpGet("{id}")]
    [ProducesResponseType(typeof(ClientResponse), StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status404NotFound)]
    public async Task<ActionResult<ClientResponse>> GetClientById(int id)
    {
        var client = await _clientService.GetClientByIdAsync(id);
        if (client == null)
        {
            return NotFound($"Клиент с ID {id} не найден");
        }
        
        return Ok(client);
    }
    
    [HttpPost("search")]
    [ProducesResponseType(typeof(List<ClientResponse>), StatusCodes.Status200OK)]
    public async Task<ActionResult<List<ClientResponse>>> SearchClients([FromBody] SearchClientRequest request)
    {
        var clients = await _clientService.SearchClientsByNameAsync(request.Name);
        return Ok(clients);
    }
    
    [HttpPut]
    [ProducesResponseType(typeof(ClientResponse), StatusCodes.Status200OK)]
    [ProducesResponseType(StatusCodes.Status404NotFound)]
    [ProducesResponseType(StatusCodes.Status400BadRequest)]
    public async Task<ActionResult<ClientResponse>> UpdateClient([FromBody] UpdateClientRequest request)
    {
        if (request.Id <= 0)
        {
            return BadRequest("Неверный ID");
        }
        
        if (string.IsNullOrWhiteSpace(request.Name))
        {
            return BadRequest("Имя клиента не может быть пустым");
        }
        
        var client = await _clientService.UpdateClientAsync(request);
        if (client == null)
        {
            return NotFound($"Клиент с ID {request.Id} не найден");
        }
        
        return Ok(client);
    }
    
    [HttpDelete("{id}")]
    [ProducesResponseType(StatusCodes.Status204NoContent)]
    [ProducesResponseType(StatusCodes.Status404NotFound)]
    public async Task<IActionResult> DeleteClient(int id)
    {
        var deleted = await _clientService.DeleteClientAsync(id);
        if (!deleted)
        {
            return NotFound($"Клиент с ID {id} не найден");
        }
        
        return NoContent();
    }
}