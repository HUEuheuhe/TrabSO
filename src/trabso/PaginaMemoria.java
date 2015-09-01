package trabso;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "[ Pagina (id=" + id + ") ]  ";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaginaMemoria other = (PaginaMemoria) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
