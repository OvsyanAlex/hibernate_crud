import org.example.model.Post;
import org.example.model.Status;
import org.example.repository.PostRepositoryImpl;
import org.example.service.PostService;

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
public class PostServiceTest {

    @InjectMocks
    PostService postService;

    @Mock
    PostRepositoryImpl postRepository;

    Post post;
    long id;

    @BeforeEach
    void setUp() {
        id = 1L;
        post = new Post(id, "PostTitle", "PostContent", Status.ACTIVE, id);
    }

    @Tag("unit")
    @Test
    public void givenWriterShouldSave() {

        given(postRepository.save(post)).willReturn(post);
        Post result = postService.save(post);
        then(postRepository).should().save(post);
        assertAll(
                () -> assertEquals("PostTitle", result.getTitle()),
                () -> assertEquals("PostContent", result.getContent()),
                () -> assertEquals(Status.ACTIVE, result.getStatus()),
                () -> assertEquals(id, result.getId()));
    }

    @Tag("unit")
    @Test
    public void shouldGetWriterById() {
        given(postRepository.getById(1L)).willReturn(post);
        Post result = postService.getById(id);
        then(postRepository).should().getById(id);
        assertAll(
                () -> assertEquals("PostTitle", result.getTitle()),
                () -> assertEquals("PostContent", result.getContent()),
                () -> assertEquals(Status.ACTIVE, result.getStatus()),
                () -> assertEquals(id, result.getId()));
    }

    @Tag("unit")
    @Test
    public void givenWriterShouldDelete() {
        willDoNothing().given(postRepository).deleteById(id);
        postService.deleteById(id);
        then(postRepository).should().deleteById(id);
    }

    @Tag("unit")
    @Test
    public void shouldReturnAll() {
        ArrayList<Post> expected = new ArrayList<>(List.of(post, post));
        when(postRepository.findAll()).thenReturn(expected);
        List<Post> actual = postService.findAll();
        then(postRepository).should().findAll();
        assertEquals(2, actual.size());
    }

    @Tag("unit")
    @Test
    public void givenWriterShouldUpdate() {
        doReturn(post).when(postRepository).update(post);
        Post result = postService.update(post);

        then(postRepository).should().update(post);
        assertAll(
                () -> assertEquals("PostTitle", result.getTitle()),
                () -> assertEquals("PostContent", result.getContent()),
                () -> assertEquals(Status.ACTIVE, result.getStatus()),
                () -> assertEquals(id, result.getId()));
    }
}
