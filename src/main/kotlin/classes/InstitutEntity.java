package classes;

public class InstitutEntity {
    private String codi;
    private String nom;
    private String adreca;
    private String numero;
    private Integer codpostal;
//    private Integer codM;
    private PoblacioEntity poblacio;

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdreca() {
        return adreca;
    }

    public void setAdreca(String adreca) {
        this.adreca = adreca;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCodpostal() {
        return codpostal;
    }

    public void setCodpostal(Integer codpostal) {
        this.codpostal = codpostal;
    }

//    public Integer getCodM() {
//        return codM;
//    }

//    public void setCodM(Integer codM) {
//        this.codM = codM;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstitutEntity that = (InstitutEntity) o;

        if (codi != null ? !codi.equals(that.codi) : that.codi != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (adreca != null ? !adreca.equals(that.adreca) : that.adreca != null) return false;
        if (numero != null ? !numero.equals(that.numero) : that.numero != null) return false;
        if (codpostal != null ? !codpostal.equals(that.codpostal) : that.codpostal != null) return false;
//        if (codM != null ? !codM.equals(that.codM) : that.codM != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codi != null ? codi.hashCode() : 0;
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (adreca != null ? adreca.hashCode() : 0);
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (codpostal != null ? codpostal.hashCode() : 0);
//        result = 31 * result + (codM != null ? codM.hashCode() : 0);
        return result;
    }

    public PoblacioEntity getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(PoblacioEntity poblacio) {
        this.poblacio = poblacio;
    }
}
