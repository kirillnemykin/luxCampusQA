package AutomationTests.testClients;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class ClientLombok {
    private Long id;
    private String name;
    private String lastName;
    private String address;
    private Long balance;
    private Long rate;
    private String login;

}
