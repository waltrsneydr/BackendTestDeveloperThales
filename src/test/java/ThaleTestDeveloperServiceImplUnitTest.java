
import static org.mockito.Mockito.*;
        import static org.junit.jupiter.api.Assertions.*;

import com.thaleservice.dto.EmployeeDto;
import com.thaleservice.dto.EmployeesResponseDto;
import com.thaleservice.dto.OneEmployeesResponseDto;
import com.thaleservice.service.ThaleTestDeveloperServiceImpl;
import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
        import org.springframework.web.client.HttpClientErrorException;
        import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
@ExtendWith(MockitoExtension.class)
public class ThaleTestDeveloperServiceImplUnitTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ThaleTestDeveloperServiceImpl employeeService;

    private static final String BASE_URL = "https://dummy.restapiexample.com/api/v1/";

    @Test
    public void testGetAllEmployeesOk() {

        EmployeesResponseDto responseDto = new EmployeesResponseDto();
        responseDto.setData(Arrays.asList(
                new EmployeeDto(1, "Tiger Nixon",320800.0,61,"",0)
        ));

        when(restTemplate.exchange(eq(BASE_URL + "employees"),eq(HttpMethod.GET), any(), eq(EmployeesResponseDto.class))).thenReturn(ResponseEntity.ok(responseDto));

        ResponseEntity<EmployeesResponseDto> response = employeeService.getAllEmployees();
        assertNotNull(response);
        assertEquals(3849600.0, response.getBody().getData().get(0).getEmployee_anual_salary());

    }

    @Test
    public void testgetEmployeeById() {

        OneEmployeesResponseDto responseDto = new OneEmployeesResponseDto();
        String idEmployee = "1";
        when(restTemplate.exchange(eq(BASE_URL + "employee/" + idEmployee),eq(HttpMethod.GET), any(), eq(OneEmployeesResponseDto.class))).thenReturn(ResponseEntity.ok(responseDto));

        ResponseEntity<OneEmployeesResponseDto> response = employeeService.getEmployeeById("1");
        assertNotNull(response);
        assertEquals("No records found", response.getBody().getMessage());
        assertEquals("ok", response.getBody().getStatus());

    }


}

