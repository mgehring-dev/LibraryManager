import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

 public class Main
{
	public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    List<Livro> livros = new ArrayList<>();  
    List<Usuario> usuarios = new ArrayList<>();
    List<Emprestimo> emprestimos = new ArrayList<>(); 

    int opcao = -1; 

    System.out.println("== Library Manager ==");

    while (opcao != 0) {   

        System.out.println("\n0 - Sair"); 
        System.out.println("1 - Cadastrar livro");
        System.out.println("2 - Cadastrar aluno");
        System.out.println("3 - Cadastrar professor");
        System.out.println("4 - Catalogo de livros"); 
        System.out.println("5 - Emprestimo de livro"); 
        System.out.println("6 - Listar emprestimos"); 
        System.out.println("7 - Devolver livro");  
        System.out.print("Selecione uma opcao: ");

        opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("\n== Cadastro de livro ==");
                System.out.println("Titulo:");
                String titulo = scanner.nextLine();
                System.out.println("Autor:");
                String autor = scanner.nextLine();
                System.out.println("ISBN:");  
                String isbn = scanner.nextLine();  

                Livro livro = new Livro(titulo, autor, isbn, true);
                livros.add(livro);
                System.out.println("Livro '" + titulo + "' cadastrado com sucesso!");
                break;
            case 2:
            case 3:
                System.out.println("\n== Cadastro de " + (opcao == 2 ? "aluno" : "professor") + " ==");
                long id = System.currentTimeMillis();

                System.out.println("Nome:");
                String nome = scanner.nextLine(); 

                if (opcao == 2) {
                    System.out.println("Curso:");
                    String curso = scanner.nextLine(); 

                    Usuario aluno = new Aluno(id, nome, curso); 
                    usuarios.add(aluno);
                }   else {  
                    System.out.println("Departamento:");
                    String departamento = scanner.nextLine();

                    Usuario professor = new Professor(id, nome, departamento);
                    usuarios.add(professor);
                }

                System.out.println((opcao == 2 ? "Aluno" : "Professor") + " '" + nome + "' cadastrado com sucesso!"); 
                break; 
            case 4: 
                System.out.println("\n== Catalogo de livros ==");
                if (livros.isEmpty()) {
                    System.out.println("Nenhum livro disponivel.");
                } else {
                    for (Livro l : livros) { 
                        System.out.println("\n-------------------");
                        l.exibirInformacoes();
                    }
                } 
                break;
            case 5: 
                System.out.println("\n== Emprestimo de livros =="); 

                if (usuarios.isEmpty()) {
                    System.out.println("Nenhum usuario cadastrado. Cadastre um usuario antes de realizar emprestimos.");
                    break;
                }

                if (livros.isEmpty()) {
                    System.out.println("Nenhum livro disponivel.");
                    break;
                }

                for (int i = 0; i < livros.size(); i++) {
                    String label = (i + 1) + " " + livros.get(i).getTitulo() + " - " + (livros.get(i).isDisponivel() ? "Disponivel!" : "Indisponivel...");
                    System.out.println(label); 
                }
                System.out.print("Selecione o livro: ");
                opcao = scanner.nextInt();

                if (opcao > 0 && opcao <= livros.size()) {
                    Livro livroSelecionado = livros.get(opcao - 1);
                    if (livroSelecionado.isDisponivel()) {
                        livroSelecionado.setDisponivel(false); 

                        for (int i = 0; i < usuarios.size(); i++) {
                            String label = (i + 1) + " " + usuarios.get(i).getNome();
                            System.out.println(label); 
                        }
                        System.out.print("Selecione o usuario: ");
                        opcao = scanner.nextInt();

                        if (opcao > 0 && opcao <= usuarios.size()) {
                            Usuario usuarioSelecionado = usuarios.get(opcao - 1); 

                            livroSelecionado.setDisponivel(false);

                            String dataAtual = LocalDateTime.now().toString();
                            String mais7Dias = LocalDateTime.now().plusDays(7).toString();
                            Emprestimo emprestimo = new Emprestimo(livroSelecionado, usuarioSelecionado, dataAtual, mais7Dias);

                            emprestimos.add(emprestimo);
                            System.out.println("Livro '" + livroSelecionado.getTitulo() + "' emprestado para '" + usuarioSelecionado.getNome() + "' com sucesso!"); 
                        } else {
                            System.out.println("Opcao invalida. Tente novamente.");
                        }

                    } else {
                        System.out.println("Livro '" + livroSelecionado.getTitulo() + "' nao esta disponivel para emprestimo.");
                    }
                } else {
                    System.out.println("Opcao invalida. Tente novamente.");
                }

                break; 
            case 6: 
                System.out.println("\n== Lista de emprestimos =="); 
                if (emprestimos.isEmpty()) {
                    System.out.println("Nenhum emprestimo registrado.");
                } else {
                    for (Emprestimo e : emprestimos) { 
                        System.out.println("\n-------------------");
                        e.exibirResumoEmprestimo(); 
                    }
                }
                break;    
            case 7: 
                System.out.println("\n== Devolucao de livros =="); 

                List<Livro> livrosEmprestados = new ArrayList<>();
                for (Emprestimo e : emprestimos) { 
                    livrosEmprestados.add(e.getLivro()); 
                }

                if (livrosEmprestados.isEmpty()) {
                    System.out.println("Nenhum livro emprestado.");
                    break;
                }

                for (int i = 0; i < livrosEmprestados.size(); i++) {
                    String label = (i + 1) + " " + livrosEmprestados.get(i).getTitulo();
                    System.out.println(label); 
                }
                System.out.print("Selecione o livro para devolucao: ");
                opcao = scanner.nextInt();

                if (opcao > 0 && opcao <= livrosEmprestados.size()) {
                    Livro livroSelecionado = livrosEmprestados.get(opcao - 1);
                        
                    for (Emprestimo e : emprestimos) { 
                        if (e.getLivro().equals(livroSelecionado)) {
                            emprestimos.remove(e); 

                            livroSelecionado.setDisponivel(true); 
                            System.out.println("Livro '" + livroSelecionado.getTitulo() + "' devolvido com sucesso!");
                            
                            break; 
                        }
                    }   
                } else {
                    System.out.println("Opcao invalida. Tente novamente.");
                }

                break;   
            case 0:
                System.out.println("Encerrando o programa...");
                break;
            default:
                System.out.println("Opcao invalida. Tente novamente.");
        }
    }   
	}
}