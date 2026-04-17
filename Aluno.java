public class Aluno extends Usuario { 
    private String curso;

    public Aluno(long id, String nome, String curso) {
        super(id, nome);
        this.curso = curso;
    }

    public long getId() {
        return super.getId();
    }

    public String getNome() {
        return super.getNome();
    }

    public String getCurso() {
        return curso;
    }
 

    public void exibirTipoUsuario() {   
        System.out.println("\nNome: " + getNome());
        System.out.println("Curso: " + getCurso());
        System.out.println("Tipo de usuario: Aluno");
    }
}