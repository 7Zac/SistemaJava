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
    private JTextField campoSobrenome;
    private JTextField campoIdade;
    private JTextField campoEmail;

    public SistemaCadastroGUI() {
        // Aplica o tema Nimbus para uma aparência moderna
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Se não conseguir, segue com o padrão
        }

        JFrame frame = new JFrame("Cadastro de Pessoas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(new BorderLayout());

        JPanel painelEntrada = new JPanel(new GridBagLayout());
        painelEntrada.setBackground(new Color(245, 245, 250));
        painelEntrada.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fonteLabel = new Font("Segoe UI", Font.BOLD, 15);
        Font fonteCampo = new Font("Segoe UI", Font.PLAIN, 15);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setFont(fonteLabel);
        gbc.gridx = 0; gbc.gridy = 0;
        painelEntrada.add(labelNome, gbc);

        campoNome = new JTextField();
        campoNome.setFont(fonteCampo);
        campoNome.setBackground(new Color(230, 230, 240));
        gbc.gridx = 1; gbc.gridy = 0;
        painelEntrada.add(campoNome, gbc);

        JLabel labelSobrenome = new JLabel("Sobrenome:");
        labelSobrenome.setFont(fonteLabel);
        gbc.gridx = 0; gbc.gridy = 1;
        painelEntrada.add(labelSobrenome, gbc);

        campoSobrenome = new JTextField();
        campoSobrenome.setFont(fonteCampo);
        campoSobrenome.setBackground(new Color(230, 230, 240));
        gbc.gridx = 1; gbc.gridy = 1;
        painelEntrada.add(campoSobrenome, gbc);

        JLabel labelIdade = new JLabel("Idade:");
        labelIdade.setFont(fonteLabel);
        gbc.gridx = 0; gbc.gridy = 2;
        painelEntrada.add(labelIdade, gbc);

        campoIdade = new JTextField();
        campoIdade.setFont(fonteCampo);
        campoIdade.setBackground(new Color(230, 230, 240));
        gbc.gridx = 1; gbc.gridy = 2;
        painelEntrada.add(campoIdade, gbc);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setFont(fonteLabel);
        gbc.gridx = 0; gbc.gridy = 3;
        painelEntrada.add(labelEmail, gbc);

        campoEmail = new JTextField();
        campoEmail.setFont(fonteCampo);
        campoEmail.setBackground(new Color(230, 230, 240));
        gbc.gridx = 1; gbc.gridy = 3;
        painelEntrada.add(campoEmail, gbc);

        JButton botaoAdicionar = new JButton("Adicionar");
        botaoAdicionar.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botaoAdicionar.setBackground(new Color(76, 175, 80));
        botaoAdicionar.setForeground(Color.WHITE);
        botaoAdicionar.setFocusPainted(false);
        botaoAdicionar.setBorder(new EmptyBorder(10, 10, 10, 10));
        gbc.gridx = 0; gbc.gridy = 4;
        painelEntrada.add(botaoAdicionar, gbc);

        JButton botaoImprimir = new JButton("Imprimir");
        botaoImprimir.setFont(new Font("Segoe UI", Font.BOLD, 15));
        botaoImprimir.setBackground(new Color(33, 150, 243));
        botaoImprimir.setForeground(Color.WHITE);
        botaoImprimir.setFocusPainted(false);
        botaoImprimir.setBorder(new EmptyBorder(10, 10, 10, 10));
        gbc.gridx = 1; gbc.gridy = 4;
        painelEntrada.add(botaoImprimir, gbc);

        frame.add(painelEntrada, BorderLayout.NORTH);

        textoRelatorio = new JTextArea();
        textoRelatorio.setEditable(false);
        textoRelatorio.setFont(new Font("Consolas", Font.PLAIN, 14));
        textoRelatorio.setBackground(new Color(250, 250, 255));
        textoRelatorio.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 220)));
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

        frame.setLocationRelativeTo(null); // Centraliza na tela
        frame.setVisible(true);
    }

    private void adicionarPessoa() {
        String nome = campoNome.getText();
        String sobrenome = campoSobrenome.getText();
        String idadeText = campoIdade.getText();
        String email = campoEmail.getText();

        if (nome.isEmpty() || sobrenome.isEmpty() || idadeText.isEmpty() || email.isEmpty()) {
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

        Pessoa pessoa = new Pessoa(nome, sobrenome, idade, email);
        pessoas.add(pessoa);
        atualizarRelatorio();

        campoNome.setText("");
        campoSobrenome.setText("");
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