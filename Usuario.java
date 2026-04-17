public class Usuario {
    private long id;
    private String nome; 

    public Usuario(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
 

    public void exibirTipoUsuario() {
        System.out.println("Tipo de usuario: Usuario padrao");
    }
}