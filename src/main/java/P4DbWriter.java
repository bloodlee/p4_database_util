import com.perforce.p4java.client.IClient;
import com.perforce.p4java.core.IChangelist;
import com.perforce.p4java.core.IChangelistSummary;
import com.perforce.p4java.core.file.DiffType;
import com.perforce.p4java.core.file.IFileSpec;
import com.perforce.p4java.env.SystemInfo;
import com.perforce.p4java.exception.*;
import com.perforce.p4java.option.server.ChangelistOptions;
import com.perforce.p4java.server.IOptionsServer;
import com.perforce.p4java.server.IServer;
import com.perforce.p4java.server.ServerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yli on 3/5/2016.
 */
public class P4DbWriter {

  public static void main(String[] args) {
    try {
      IOptionsServer server = ServerFactory.getOptionsServer("p4java://127.0.0.1:1666", null);
      server.connect();

      server.setUserName("yli");
      server.login("iloveyou", true);

      IClient client = server.getClient("first_workspace");
      System.out.println(client.toString());

      ChangelistOptions options = new ChangelistOptions();
      options.setOriginalChangelist(true);

      List<IChangelistSummary> changelists =
          server.getChangelists(
              10, // max most recent
              new ArrayList<IFileSpec>(), // file specs
              null, // client name,
              null, // username,
              true, // include integrated
              true, // submitted only
              true, // pending only
              false); // long desc

      for (IChangelistSummary changelist : changelists) {
        System.out.println(changelist.getClientId());
        System.out.println(changelist.getUsername());
        System.out.println(changelist.getDate());
        System.out.println(changelist.getDescription());
        System.out.println(changelist.getId());
        System.out.println(changelist.getStatus());
        System.out.println(changelist.getVisibility());


      }

      IChangelist changelist1 = server.getChangelist(7 , options);
      System.out.println("Original CL " + changelist1.getId());


    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (ConnectionException e) {
      e.printStackTrace();
    } catch (NoSuchObjectException e) {
      e.printStackTrace();
    } catch (ConfigException e) {
      e.printStackTrace();
    } catch (ResourceException e) {
      e.printStackTrace();
    } catch (AccessException e) {
      e.printStackTrace();
    } catch (RequestException e) {
      e.printStackTrace();
    } catch (P4JavaException e) {
      e.printStackTrace();
    }
  }

}
