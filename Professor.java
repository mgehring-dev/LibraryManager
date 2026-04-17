public class Professor extends Usuario { 
    private String departamento;

    public Professor(long id, String nome, String departamento) {
        super(id, nome);
        this.departamento = departamento;
    }

    public long getId() {
        return super.getId();
    }

    public String getNome() {
        return super.getNome();
    }

    public String getDepartamento() {
        return departamento;
    }
 

    public void exibirTipoUsuario() {
        System.out.println("\nNome: " + getNome());
        System.out.println("Departamento: " + getDepartamento());
        System.out.println("Tipo de usuario: Professor");
    }
}