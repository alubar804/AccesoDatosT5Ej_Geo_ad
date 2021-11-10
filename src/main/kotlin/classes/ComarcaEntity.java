package classes;

import java.util.Collection;

public class ComarcaEntity {
    private String nomC;
    private String provincia;
    private Collection<PoblacioEntity> poblacions;

    public String getNomC() {
        return nomC;
    }

    public void setNomC(String nomC) {
        this.nomC = nomC;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ComarcaEntity that = (ComarcaEntity) o;

        if (nomC != null ? !nomC.equals(that.nomC) : that.nomC != null) return false;
        if (provincia != null ? !provincia.equals(that.provincia) : that.provincia != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = nomC != null ? nomC.hashCode() : 0;
        result = 31 * result + (provincia != null ? provincia.hashCode() : 0);
        return result;
    }

    public Collection<PoblacioEntity> getPoblacions() {
        return poblacions;
    }

    public void setPoblacions(Collection<PoblacioEntity> poblacions) {
        this.poblacions = poblacions;
    }
}
