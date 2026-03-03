using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace NetArticle.Models;

[Table("clients")]
public class Client
{
    [Key]
    [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
    [Column("id")]
    public int Id { get; set; }
    
    [Required]
    [Column("name")]
    [MaxLength(255)]
    public string Name { get; set; } = string.Empty;
    
    public Client() { }
    
    public Client(string name)
    {
        Name = name;
    }
    
    public Client(int id, string name)
    {
        Id = id;
        Name = name;
    }
}