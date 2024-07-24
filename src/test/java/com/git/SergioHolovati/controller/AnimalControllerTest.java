package com.git.SergioHolovati.controller;

import com.git.SergioHolovati.dto.AnimalDTO;
import com.git.SergioHolovati.dto.AnimalRequestDTO;
import com.git.SergioHolovati.enums.AnimalCategoryEnum;
import com.git.SergioHolovati.enums.AnimalStatusEnum;
import com.git.SergioHolovati.service.AnimalService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AnimalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AnimalService service;

    @InjectMocks
    private AnimalController controller;

    private AnimalDTO animalDTO;
    private AnimalRequestDTO requestDTO;
    private Page<AnimalDTO> animalPage;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        animalDTO = AnimalDTO.builder().id("1").build();
        requestDTO = AnimalRequestDTO.builder().build();
        animalPage = new PageImpl<>(Collections.singletonList(animalDTO), PageRequest.of(0, 10), 1);
    }

    @Test
    void getAllAvaiable() throws Exception {
        when(service.findAllAvaiable(any())).thenReturn(animalPage);

        mockMvc.perform(get("/animals/avaiable")
                .param("page", "0")
                .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0]").exists());

        verify(service, times(1)).findAllAvaiable(any());
    }

    @Test
    void getById_Success() throws Exception {
        when(service.findById("1")).thenReturn(animalDTO);

        mockMvc.perform(get("/animals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(animalDTO.getId()));

        verify(service, times(1)).findById("1");
    }

    @Test
    void save() throws Exception {
        when(service.save(any())).thenReturn(animalDTO);

        mockMvc.perform(post("/animals")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Dog\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(animalDTO.getId()));

        verify(service, times(1)).save(any());
    }

    @Test
    void getStatus() throws Exception {
        List<Map<String, String>> statusList = Collections.singletonList(Collections.singletonMap("key", "value"));
        when(service.status()).thenReturn(statusList);

        mockMvc.perform(get("/animals/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].key", is("value")));

        verify(service, times(1)).status();
    }

    @Test
    void getCategory() throws Exception {
        List<Map<String, String>> categoryList = Collections.singletonList(Collections.singletonMap("key", "value"));
        when(service.category()).thenReturn(categoryList);

        mockMvc.perform(get("/animals/category"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].key", is("value")));

        verify(service, times(1)).category();
    }
}