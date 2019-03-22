package br.com.besc.view;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import br.com.besc.model.Cliente;
import br.com.besc.model.Conta;
import br.com.besc.model.ContaCorrente;
import br.com.besc.model.ContaPoupanca;

public class CaixaEletronico {

	private static ArrayList<Cliente> listaDeClientes = new ArrayList<>();

	public static void main(String[] args) {
		int opcao = 0;
		while (opcao != 9) {
			opcao = Integer.parseInt(JOptionPane.showInputDialog("1 - Acessar conta \n2 - Criar conta \n9 - Sair"));
			if (opcao == 1) {
				acessarConta();
			} else if (opcao == 2) {
				criarConta();
			} else if (opcao != 9) {
				JOptionPane.showMessageDialog(null, "Opção invalida!");
			}
		}
	}

	private static void acessarConta() {
		String cpf = JOptionPane.showInputDialog("Digite seu CPF:");
		String senha = JOptionPane.showInputDialog("Digite seu senha:");
		Cliente clienteLogado = null;
		for (int i = 0; i < listaDeClientes.size(); i++) {
			Cliente cliente = listaDeClientes.get(i);
			if (cliente.getCpf().equals(cpf) && cliente.getSenha().equals(senha)) {
				clienteLogado = cliente;
			}
		}
		if (clienteLogado == null) {
			JOptionPane.showMessageDialog(null, "Dados invalidos!!");
		} else {
			mostrarMenuOperacoes(clienteLogado);
		}
	}

	private static void mostrarMenuOperacoes(Cliente clienteLogado) {
		int menuSelecionado = 0;
		while (menuSelecionado != 11) {
			String menus = "1 - Extrato\n" + 
							"2 - Saldo\n" + 
							"3 - Pagamento\n" + 
							"4 - Transferencia\n" + 
							"5 - Deposito\n" + 
							"6 - Saque\n";
			if (clienteLogado.getConta().codigoOperacao() == 1) {
				menus += "7 - Consulta limite\n" + 
							"8 - Retirar cheque\n" + 
							"9 - Debito automatico\n";
			}
			menus += "10 - Alteração cadastral\n" 
					+ "11 - Sair";
			menuSelecionado = Integer.parseInt(JOptionPane.showInputDialog(menus));
			switch (menuSelecionado) {
			case 1:
				String extrato = clienteLogado.getConta().gerarExtrato();
				JOptionPane.showMessageDialog(null, extrato);
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Saldo atual: " + clienteLogado.getConta().getSaldo());
				break;
			case 3:
				
				break;
			case 4:

				break;
			case 5:
				double valor = Double.parseDouble(JOptionPane.showInputDialog("Valor de deposito:"));
				clienteLogado.getConta().depositar(valor);
				break;
			case 6:
				valor = Double.parseDouble(JOptionPane.showInputDialog("Valor de saque:"));
				if (!clienteLogado.getConta().sacar(valor)) {
					JOptionPane.showMessageDialog(null, "Saldo insuficiente");
				}
				break;
			case 7:
				JOptionPane.showMessageDialog(null, "Limite atual: " + ((ContaCorrente) clienteLogado.getConta()).getLimite());
				break;
			case 8:

				break;
			case 9:

				break;
			case 10:

				break;
			case 11:

				break;

			default:
				break;
			}
		}
	}

	private static void criarConta() {
		String nome = JOptionPane.showInputDialog("Digite seu nome: ");
		String cpf = JOptionPane.showInputDialog("Digite seu cpf: ");
		String senha = JOptionPane.showInputDialog("Digite seu senha: ");

		Cliente cliente = new Cliente(nome, cpf, senha);

		String telefone = JOptionPane.showInputDialog("Digite seu telefone: ");
		String email = JOptionPane.showInputDialog("Digite seu email: ");
		cliente.setTelefone(telefone);
		cliente.setEmail(email);

		int tipoConta = Integer.parseInt(JOptionPane.showInputDialog("1 - Conta corrente \n2 - Conta poupança"));
		Conta conta;
		if (tipoConta == 1) {
			conta = new ContaCorrente();
		} else {
			conta = new ContaPoupanca();
		}
		conta.setSaldo(0);
		conta.setAgencia(1010);
		conta.setNumeroConta(listaDeClientes.size() + 1);

		cliente.setConta(conta);

		listaDeClientes.add(cliente);
		JOptionPane.showMessageDialog(null, "Conta criada com sucesso!\nNumero da conta: " + conta.getNumeroConta()
				+ "\nAgencia: " + conta.getAgencia() + "\nOperação: " + conta.codigoOperacao());
	}
}
