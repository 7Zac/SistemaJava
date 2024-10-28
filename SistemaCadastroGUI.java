import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.util.ArrayList;
import java.util.List;

public class SistemaCadastroGUI implements Printable {
    private List<Pessoa> pessoas = new ArrayList<>();
    private JTextArea textoRelatorio;
    private JTextField campoNome;
    private JTextField campoIdade;
    private JTextField campoEmail;

    public SistemaCadastroGUI() {
        JFrame frame = new JFrame("Cadastro de Pessoas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 350);
        frame.setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel(new GridLayout(5, 2, 10, 10)); // Espaçamento de 10 pixels
        painelEntrada.setBorder(new EmptyBorder(20, 20, 20, 20)); // Margem do painel
        
        painelEntrada.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painelEntrada.add(campoNome);

        painelEntrada.add(new JLabel("Idade:"));
        campoIdade = new JTextField();
        painelEntrada.add(campoIdade);

        painelEntrada.add(new JLabel("Email:"));
        campoEmail = new JTextField();
        painelEntrada.add(campoEmail);

        JButton botaoAdicionar = new JButton("Adicionar");
        painelEntrada.add(botaoAdicionar);

        botaoAdicionar.setBorder(new EmptyBorder(10, 10, 10, 10));
        botaoAdicionar.setPreferredSize(new Dimension(15, 40));

        JButton botaoImprimir = new JButton("Imprimir");
        painelEntrada.add(botaoImprimir);

        botaoImprimir.setBorder(new EmptyBorder(10, 10, 10, 10));
        botaoImprimir.setPreferredSize(new Dimension(15, 40));
        
        frame.add(painelEntrada, BorderLayout.NORTH);

        textoRelatorio = new JTextArea();
        textoRelatorio.setEditable(false);
        frame.add(new JScrollPane(textoRelatorio), BorderLayout.CENTER);

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarPessoa();
            }
        });

        botaoImprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirRelatorio();
            }
        });


        //frame.add(painelEntrada);
        frame.setVisible(true);
    }

    private void adicionarPessoa() {
        String nome = campoNome.getText();
        String idadeText = campoIdade.getText();
        String email = campoEmail.getText();

        if (nome.isEmpty() || idadeText.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.");
            return;
        }

        int idade;
        try {
            idade = Integer.parseInt(idadeText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Idade deve ser um número.");
            return;
        }

        Pessoa pessoa = new Pessoa(nome, idade, email);
        pessoas.add(pessoa);
        atualizarRelatorio();

        campoNome.setText("");
        campoIdade.setText("");
        campoEmail.setText("");

        

    }

    private void atualizarRelatorio() {
        StringBuilder relatorio = new StringBuilder();
        for (Pessoa pessoa : pessoas) {
            relatorio.append(pessoa).append("\n");
        }
        textoRelatorio.setText(relatorio.toString());
    }

    private void imprimirRelatorio() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);

        boolean doPrint = job.printDialog();
        if (doPrint) {
            try {
                job.print();
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(null, "Erro ao tentar imprimir: " + e.getMessage());
            }
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        g.drawString("Relatório de Pessoas Cadastradas", 100, 100);
        int y = 120;

        for (Pessoa pessoa : pessoas) {
            g.drawString(pessoa.toString(), 100, y);
            y += 15;
        }

        return PAGE_EXISTS;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaCadastroGUI::new);
    }
    
}

