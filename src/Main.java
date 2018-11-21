import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

/**
 * <p>
 * Exemplo de c�digo para gera��o de um boleto simples.
 * </p>
 * <p>
 * Utiliza o Banco Bradesco como exemplo, j� que possui um implementa��o simples.
 * </p>
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author <a href="mailto:misaelbarreto@gmail.com">Misael Barreto</a>
 * @author <a href="mailto:romulomail@gmail.com">R�mulo Augusto</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class Main {

        public static void main(String[] args) throws ParseException {
        	
        		JTextField field1 = new JTextField();
        		JTextField field2 = new JTextField();
        		Object[] fields = {"Nome da Empresa",field1,"CNPJ(##.###.###/####-##)",field2};
        		JOptionPane.showConfirmDialog(null,fields,"Dados do Cedente",JOptionPane.OK_CANCEL_OPTION);
                /*
                 * INFORMANDO DADOS SOBRE O CEDENTE.
                 */
                Cedente cedente = new Cedente(field1.getText(),field2.getText());
                
                JTextField field3 = new JTextField();
        		JTextField field4 = new JTextField();
        		JTextField field5 = new JTextField();
        		JTextField field6 = new JTextField();
        		JTextField field7 = new JTextField();
        		JTextField field8 = new JTextField();
        		JTextField field9 = new JTextField();
        		JTextField field10 = new JTextField();
        		
        		Object[] fields2 = {"Nome",field3,"CPF",field4,"UF(Sigla)",field5,"Localidade",field6,"CEP",field7,"Bairro",field8,"Logradouro",field9,"Número",field10};
        		JOptionPane.showConfirmDialog(null,fields2,"Dados do Sacado",JOptionPane.OK_CANCEL_OPTION);
                /*
                 * INFORMANDO DADOS SOBRE O SACADO.
                 */
                Sacado sacado = new Sacado(field3.getText(),field4.getText());
                
                // Informando o endere�o do sacado.
                Endereco enderecoSac = new Endereco();
                // Tratando Unidade Federativa
                for (UnidadeFederativa un : UnidadeFederativa.values()) {
                	if(un.name().equalsIgnoreCase(field5.getText())) {
                		enderecoSac.setUF(un);
                		break;
                	}
                }
                //Fim do tratamento
                enderecoSac.setLocalidade(field6.getText());
                enderecoSac.setCep(new CEP(field7.getText()));
                enderecoSac.setBairro(field8.getText());
                enderecoSac.setLogradouro(field9.getText());
                enderecoSac.setNumero(field10.getText());
                sacado.addEndereco(enderecoSac);
                
                JTextField field11 = new JTextField();
        		JTextField field12 = new JTextField();
        		JTextField field13 = new JTextField();
        		JTextField field14 = new JTextField();
        		JTextField field15 = new JTextField();
        		JTextField field16 = new JTextField();
        		JTextField field17 = new JTextField();
        		JTextField field18 = new JTextField();
        		
        		Object[] fields3 = {"Nome",field11,"CNPJ(##.###.###/####-##)",field12,"UF(Sigla)",field13,"Localidade",field14,"CEP",field15,"Bairro",field16,"Logradouro",field17,"Número",field18};
        		JOptionPane.showConfirmDialog(null,fields3,"Dados do Sacado Avalista",JOptionPane.OK_CANCEL_OPTION);
                /*
                 * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
                 */
                SacadorAvalista sacadorAvalista = new SacadorAvalista(field11.getText(),field12.getText());

                // Informando o endere�o do sacador avalista.
                Endereco enderecoSacAval = new Endereco();
                // Tratando Unidade Federativa
                for (UnidadeFederativa un : UnidadeFederativa.values()) {
                	if(un.name().equalsIgnoreCase(field13.getText())) {
                		enderecoSacAval.setUF(un);
                		break;
                	}
                }
                //Fim do tratamento
                //enderecoSacAval.setUF(UnidadeFederativa.DF);
                enderecoSacAval.setLocalidade(field14.getText());
                enderecoSacAval.setCep(new CEP(field15.getText()));
                enderecoSacAval.setBairro(field16.getText());
                enderecoSacAval.setLogradouro(field17.getText());
                enderecoSacAval.setNumero(field18.getText());
                sacadorAvalista.addEndereco(enderecoSacAval);

                /*
                 * INFORMANDO OS DADOS SOBRE O T�TULO.
                 */
                JTextField field19 = new JTextField();
                JTextField field20 = new JTextField();
                JTextField field21 = new JTextField();
                JTextField field22 = new JTextField();
                JTextField field23 = new JTextField();
                
                Object[] fields4 = {"Número",field19,"Dígito",field23,"Código da Carteira",field20,"Agência",field21,"Digito",field22};
                JOptionPane.showConfirmDialog(null,fields4,"Dados da Conta Bradesco",JOptionPane.OK_CANCEL_OPTION);
                
                // Informando dados sobre a conta banc�ria do t�tulo.
                ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
                contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.parseInt(field19.getText()),field23.getText()));
                contaBancaria.setCarteira(new Carteira(Integer.parseInt(field20.getText())));
                contaBancaria.setAgencia(new Agencia(Integer.parseInt(field21.getText()), field22.getText()));
                
                JTextField field24 = new JTextField();
                JTextField field25 = new JTextField();
                JTextField field26 = new JTextField();
                JTextField field27 = new JTextField();
                JTextField field38 = new JTextField();
                
                Object[] fields5 = {"Número do Documento",field24,"Identificação do título",field25,"Dígito do título",field26,"Valor do Pagamento(II,DD)",field38,"Data de Vencimento(dd/mm/aaaa)",field27};
                JOptionPane.showConfirmDialog(null,fields5,"Dados do Título",JOptionPane.OK_CANCEL_OPTION);
                
                final NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                final Number valor = format.parse(field38.getText());
                final BigDecimal valorBD = new BigDecimal(valor.toString());
                
                Titulo titulo = new Titulo(contaBancaria, sacado, cedente, sacadorAvalista);
                titulo.setNumeroDoDocumento(field24.getText());
                titulo.setNossoNumero(field25.getText());
                titulo.setDigitoDoNossoNumero(field26.getText());
                titulo.setValor(valorBD);              
                titulo.setDataDoDocumento(new Date());
                Date df = new SimpleDateFormat("dd/MM/yyyy").parse(field27.getText());  
                titulo.setDataDoVencimento(df);
                titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
                titulo.setAceite(Aceite.A);
                titulo.setDesconto(new BigDecimal(0.05));
                titulo.setDeducao(BigDecimal.ZERO);
                titulo.setMora(BigDecimal.ZERO);
                titulo.setAcrecimo(BigDecimal.ZERO);
                titulo.setValorCobrado(BigDecimal.ZERO);

                /*
                 * INFORMANDO OS DADOS SOBRE O BOLETO.
                 */
                JTextField field28 = new JTextField();
                JTextField field29 = new JTextField();
                JTextField field30 = new JTextField();
                JTextField field31 = new JTextField();
                JTextField field32 = new JTextField();
                JTextField field33 = new JTextField();
                JTextField field34 = new JTextField();
                JTextField field35 = new JTextField();
                JTextField field36 = new JTextField();
                JTextField field37 = new JTextField();
                
                Object[] fields6 = {"Local de Pagamento",field28,"Instrução ao Sacado",field29,"Instrução 1",field30
                		,"Instrução 2",field31,"Instrução 3",field32,"Instrução 4",field33,"Instrução 5",field34,
                		"Instrução 6",field35,"Instrução 7",field36,"Instrução 7",field37};
                /*Object[] fields6 = {"Local de Pagamento",field28,"Instru��o ao Sacado",field29,"Instru��o 1",field30};*/
                
                JOptionPane.showConfirmDialog(null,fields6,"Instruções Boleto",JOptionPane.OK_CANCEL_OPTION);
                
                Boleto boleto = new Boleto(titulo);
                
                boleto.setLocalPagamento(field28.getText());
                boleto.setInstrucaoAoSacado(field29.getText());
                boleto.setInstrucao1(field30.getText());
                boleto.setInstrucao2(field31.getText());
                boleto.setInstrucao3(field32.getText());
                boleto.setInstrucao4(field33.getText());
                boleto.setInstrucao5(field34.getText());
                boleto.setInstrucao6(field35.getText());
                boleto.setInstrucao7(field36.getText());
                boleto.setInstrucao8(field37.getText());

                /*
                 * GERANDO O BOLETO BANC�RIO.
                 */
                // Instanciando um objeto "BoletoViewer", classe respons�vel pela
                // gera��o do boleto banc�rio.
                BoletoViewer boletoViewer = new BoletoViewer(boleto);

                // Gerando o arquivo. No caso o arquivo mencionado ser� salvo na mesma
                // pasta do projeto. Outros exemplos:
                // WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
                // LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
                File arquivoPdf = boletoViewer.getPdfAsFile("MeuPrimeiroBoleto.pdf");

                // Mostrando o boleto gerado na tela.
                mostreBoletoNaTela(arquivoPdf);
        }

        /**
         * Exibe o arquivo na tela.
         * 
         * @param arquivoBoleto
         */
        private static void mostreBoletoNaTela(File arquivoBoleto) {

                java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                
                try {
                        desktop.open(arquivoBoleto);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
}
