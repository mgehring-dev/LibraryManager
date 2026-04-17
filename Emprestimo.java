public class Emprestimo {
    private Livro livro;
    private Usuario usuario; 
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo(Livro livro, Usuario usuario, String dataEmprestimo, String dataDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void exibirResumoEmprestimo() {
        System.out.println("\nResumo do Emprestimo:");
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuario: " + usuario.getNome());
         
        usuario.exibirTipoUsuario();

        System.out.println("Data de Emprestimo: " + dataEmprestimo);
        System.out.println("Data de Devolucao: " + dataDevolucao); 
    }
}