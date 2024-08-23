package br.com.fidias.ofx.web.resources;

import com.webcohesion.ofx4j.domain.data.MessageSetType;
import com.webcohesion.ofx4j.domain.data.ResponseEnvelope;
import com.webcohesion.ofx4j.domain.data.ResponseMessageSet;
import com.webcohesion.ofx4j.domain.data.banking.BankAccountDetails;
import com.webcohesion.ofx4j.domain.data.banking.BankStatementResponseTransaction;
import com.webcohesion.ofx4j.domain.data.banking.BankingResponseMessageSet;
import com.webcohesion.ofx4j.domain.data.common.Transaction;
import com.webcohesion.ofx4j.io.AggregateUnmarshaller;
import com.webcohesion.ofx4j.io.OFXParseException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.SortedSet;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

/**
 *
 * @author atila
 */
@Path("importar/ofx")
public class ImportarOFX {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response importarOFX(
            @MultipartForm
            ImportarArquivoFormEntity form
    ) {
        try {
            AggregateUnmarshaller<ResponseEnvelope> unmarshaller = new AggregateUnmarshaller<>(ResponseEnvelope.class);
            ResponseEnvelope re = (ResponseEnvelope) unmarshaller.unmarshal(new ByteArrayInputStream(form.getArquivo()));
            // ResponseEnvelope re = unmarshaller.unmarshal(new InputStreamReader(new FileInputStream("/tmp/Extrato-22-05-2024-a-21-06-2024.ofx"), Charset.forName("UTF-8")));
            SortedSet<ResponseMessageSet> messageSets = re.getMessageSets();
            System.out.println("empty? " + (messageSets != null ? messageSets.isEmpty() : true));
            ResponseMessageSet message = re.getMessageSet(MessageSetType.banking);
            List<BankStatementResponseTransaction> bank = ((BankingResponseMessageSet) message).getStatementResponses();
            System.out.println("size: " + bank.size());
            for (BankStatementResponseTransaction conta : bank) {
                BankAccountDetails account = conta.getMessage().getAccount();
                System.out.println("BankId: " + account.getBankId());
                System.out.println("AccountNumber: " + account.getAccountNumber());
                List<Transaction> transacoes = conta.getMessage().getTransactionList().getTransactions();
                for (Transaction transacao : transacoes) {
                    System.out.println("Amount: " + transacao.getAmount());
                }
            }
        } catch (OFXParseException | IOException e) {
            e.printStackTrace();
        }
        return Response.noContent().build();
    }
}
