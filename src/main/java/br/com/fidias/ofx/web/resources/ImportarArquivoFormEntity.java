package br.com.fidias.ofx.web.resources;

import javax.ws.rs.FormParam;

/**
 *
 * @author atila
 */
public class ImportarArquivoFormEntity {

    @FormParam("arquivo")
    private byte[] arquivo;

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }
}
