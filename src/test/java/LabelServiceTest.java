import org.example.model.Label;
import org.example.model.Status;
import org.example.repository.LabelRepositoryImpl;
import org.example.service.LabelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(MockitoExtension.class)
public class LabelServiceTest {

    @InjectMocks
    LabelService labelService;

    @Mock
    LabelRepositoryImpl labelRepository;

    Label label;
    long id;

    @BeforeEach
    void setUp() {
        id = 1L;
        label = new Label(id, "labelName", Status.ACTIVE, id);
    }

    @Tag("unit")
    @Test
    public void givenWriterShouldSave() {

        given(labelRepository.save(label)).willReturn(label);
        Label result = labelService.save(label);
        then(labelRepository).should().save(label);
        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals("labelName", result.getName()),
                () -> assertEquals(Status.ACTIVE, result.getStatus()),
                () -> assertEquals(id, result.getPostId()));
    }

    @Tag("unit")
    @Test
    public void shouldGetWriterById() {
        given(labelRepository.getById(1L)).willReturn(label);
        Label result = labelService.getById(id);
        then(labelRepository).should().getById(id);
        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals("labelName", result.getName()),
                () -> assertEquals(Status.ACTIVE, result.getStatus()),
                () -> assertEquals(id, result.getPostId()));
    }

    @Tag("unit")
    @Test
    public void givenWriterShouldDelete() {
        willDoNothing().given(labelRepository).deleteById(id);
        labelService.deleteById(id);
        then(labelRepository).should().deleteById(id);
    }

    @Tag("unit")
    @Test
    public void shouldReturnAll() {
        ArrayList<Label> expected = new ArrayList<>(List.of(label, label));
        when(labelRepository.findAll()).thenReturn(expected);
        List<Label> actual = labelService.findAll();
        then(labelRepository).should().findAll();
        assertEquals(2, actual.size());
    }

    @Tag("unit")
    @Test
    public void givenWriterShouldUpdate() {
        doReturn(label).when(labelRepository).update(label);
        Label result = labelService.update(label);

        then(labelRepository).should().update(label);
        assertAll(
                () -> assertEquals(id, result.getId()),
                () -> assertEquals("labelName", result.getName()),
                () -> assertEquals(Status.ACTIVE, result.getStatus()),
                () -> assertEquals(id, result.getPostId()));
    }
}
