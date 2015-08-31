package trabso;

public class PaginaMemoria {

    private Integer id;
    private Integer contAcesso;
    private PaginaMemoria proximo;

    public PaginaMemoria() {
        contAcesso = 0;
        proximo = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContAcesso() {
        return contAcesso;
    }

    public void setContAcesso(Integer contAcesso) {
        this.contAcesso = contAcesso;
    }

    public PaginaMemoria getProximo() {
        return proximo;
    }

    public void setProximo(PaginaMemoria proximo) {
        this.proximo = proximo;
    }

}