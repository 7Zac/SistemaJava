public class Pessoa {
    private String nome;
    private String sobrenome;
    private int idade;
    private String email;

    public Pessoa(String nome, String sobrenome ,int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
        this.sobrenome = sobrenome;
        
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " " + sobrenome + ", Idade: " + idade + ", Email: " + email;
    }
}
