import org.example.model.Status;
import org.example.model.Writer;
import org.example.repository.WriterRepositoryImpl;
import org.example.service.WriterService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;



@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
class WriterServiceTest {

    @InjectMocks
    WriterService writerService;

    @Mock
    WriterRepositoryImpl writerRepository;

    Writer writer;
    long id;

    @BeforeEach
    void setUp() {
        writer = new Writer(null, "Diego", "Alvares", Status.ACTIVE, null);
        id = 1L;
    }

    @Tag("unit")
    @Test
    public void givenWriterShouldSave() {
        // задаем поведение мок-репозитория
        given(writerRepository.save(writer)).willReturn(writer);
        // тест метода сервиса
        Writer result = writerService.save(writer);
        // проверка, что сервис вызвал репозиторий
        then(writerRepository).should().save(writer);
        // проверка полей
        assertAll(() -> assertEquals("Diego", result.getFirstName()),
                () -> assertEquals("Alvares", result.getLastName()),
                () -> assertEquals(Status.ACTIVE, result.getStatus()));
    }

    @Tag("unit")
    @Test
    public void shouldGetWriterById() {
        given(writerRepository.getById(1L)).willReturn(writer);
        Writer result = writerService.getById(id);

        then(writerRepository).should().getById(id);
        assertAll(() -> assertEquals("Diego", result.getFirstName()),
                () -> assertEquals("Alvares", result.getLastName()));
    }

    @Tag("unit")
    @Test
    public void givenWriterShouldDelete() {
        willDoNothing().given(writerRepository).deleteById(id);
        writerService.deleteById(id);
        then(writerRepository).should().deleteById(id);
    }

    @Tag("unit")
    @Test
    public void shouldReturnAll() {
        ArrayList<Writer> expected = new ArrayList<>(List.of(writer, writer));
        when(writerRepository.findAll()).thenReturn(expected);
        List<Writer> actual = writerService.findAll();
        then(writerRepository).should().findAll();
        assertEquals(2, actual.size());
    }

    @Tag("unit")
    @Test
    public void givenWriterShouldUpdate() {
        // == given(writerRepository.update(writer)).willReturn(writer);
        doReturn(writer).when(writerRepository).update(writer);
        Writer result = writerService.update(writer);

        then(writerRepository).should().update(writer);
        assertAll(() -> assertEquals("Diego", result.getFirstName()),
                () -> assertEquals("Alvares", result.getLastName()));
    }
}
