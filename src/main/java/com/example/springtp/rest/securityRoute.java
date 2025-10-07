package com.example.springtp.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class securityRoute {

    @GetMapping("/")
    public ModelAndView main() {
        return new ModelAndView("/test/index");
    }

    @GetMapping("/index")
    @PreAuthorize("hasAnyRole('TEACHER','STUDENT')")
    public ModelAndView index(JwtAuthenticationToken authentication) {
        ModelAndView mv = new ModelAndView("/test/indexmain");
        mv.addObject("user", authentication);
        return mv;
    }

    @GetMapping("/teacher/dashboard")
    @PreAuthorize("hasRole('TEACHER')")
    public ModelAndView teacherDashboard(JwtAuthenticationToken authentication) {
        ModelAndView mv = new ModelAndView("/test/admin");
        mv.addObject("user", authentication);
        return mv;
    }

    @GetMapping("/student/dashboard")
    @PreAuthorize("hasRole('STUDENT')")
    public ModelAndView studentDashboard(JwtAuthenticationToken authentication) {
        ModelAndView mv = new ModelAndView("/test/indexmain");
        mv.addObject("user", authentication);
        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView logout() {
        return new ModelAndView("/test/logout");
    }
}

