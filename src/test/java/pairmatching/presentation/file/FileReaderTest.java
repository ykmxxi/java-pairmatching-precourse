package pairmatching.presentation.file;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FileReaderTest {

    @DisplayName("백엔드 크루들을 파일 입출력으로 읽어온다.")
    @Test
    void 백엔드_크루_읽어오기() {
        List<String> crews = FileReader.readBackendCrews();

        assertThat(crews).hasSizeGreaterThan(0);
    }

    @DisplayName("프론트엔드 크루들을 파일 입출력으로 읽어온다.")
    @Test
    void 프론트엔드_크루_읽어오기() {
        List<String> crews = FileReader.readFrontendCrews();

        assertThat(crews).hasSizeGreaterThan(0);
    }

}
