package com.example.springplayground;


import com.example.springplayground.testutil.HappyPathTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MathController.class)
@ExtendWith({
        SpringExtension.class,
        MockitoExtension.class
})
class MathControllerTest {

    @SpyBean
    private MathHelper mathHelper;

    @Autowired
    private MockMvc mvc;

    private RequestBuilder request;

    @HappyPathTest
    void pi_givesPi() throws Exception {
        request = get("/math/pi");
        mvc.perform(request).andExpect(status().isOk());
    }

    @HappyPathTest
    void calculate_add_happyPath() throws Exception {
        request = get("/math/calculate?operation=add&x=3&y=4");
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void calculate_missingRequiredParams_400() throws Exception {
        request = get("/math/calculate");
        mvc.perform(request).andExpect(status().isBadRequest());
        request = get("/math/calculate?x=3");
        mvc.perform(request).andExpect(status().isBadRequest());
        request = get("/math/calculate?y=3");
        mvc.perform(request).andExpect(status().isBadRequest());
        request = get("/math/calculate?operation=add");
        mvc.perform(request).andExpect(status().isBadRequest());
    }

    @Test
    void calculate_requiredParamsBadInput_400() throws Exception {
        request = get("/math/calculate?x=&y=");
        mvc.perform(request).andExpect(status().isBadRequest());
        request = get("/math/calculate?x=cat&y=dog");
        mvc.perform(request).andExpect(status().isBadRequest());
    }

    @HappyPathTest
    void calculate_subtract() throws Exception {
        request = get("/math/calculate?operation=subtract&x=30&y=4");
        mvc.perform(request).andExpect(status().isOk());
    }

    @HappyPathTest
    void calculate_multiply() throws Exception {
        request = get("/math/calculate?operation=multiply&x=3&y=4");
        mvc.perform(request).andExpect(status().isOk());
    }

    @HappyPathTest
    void calculate_divide() throws Exception {
        request = get("/math/calculate?operation=divide&x=14&y=2");
        mvc.perform(request).andExpect(status().isOk());
    }

    @Test
    void calculate_defaultOpIsAdd() throws Exception {
        request = get("/math/calculate?x=3&y=4");
        mvc.perform(request).andExpect(status().isOk());
        assertNotNull(mathHelper);
        Mockito.verify(mathHelper).calculate(eq("add"), anyDouble(), anyDouble());
    }

    @HappyPathTest
    void sum_sums() throws Exception {
        request = get("/math/sum?n=4&n=5&n=6");
        mvc.perform(request).andExpect(status().isOk());
        request = get("/math/sum");
        mvc.perform(request).andExpect(status().isOk()).andExpect(content().string("0"));
    }

    @HappyPathTest
    void volume_getsVolume() throws Exception {
        request = get("/math/volume/3/4/5");
        mvc.perform(request).andExpect(status().isOk());
    }
}
