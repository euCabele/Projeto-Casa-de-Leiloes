/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement stmt;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        conn = new conectaDAO().connectDB();

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getValor());
            stmt.setString(3, produto.getStatus());

            stmt.executeUpdate();

            stmt.close();
            conn.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: ");

        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        String sql = "SELECT * FROM produtos";

        conn = new conectaDAO().connectDB();

        try {
            stmt = conn.prepareStatement(sql);
            resultset = stmt.executeQuery();

            while (resultset.next()) {
                ProdutosDTO obj = new ProdutosDTO();

                obj.setId(resultset.getInt("id"));
                obj.setNome(resultset.getString("nome"));
                obj.setValor(resultset.getInt("valor"));
                obj.setStatus(resultset.getString("status"));

                listagem.add(obj);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: ");
        }

        return listagem;
    }

    public void venderProduto(int id) {
        String sql = "UPDATE produtos SET status = ? WHERE id = ?";

        conn = new conectaDAO().connectDB();

        try {
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, "Vendido");
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(null, "Erro ao atualizar produto: ");
        }
    }
}
