package com.ray.jvm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面控制类
 *
 */
@Controller
public class indexContorller {

        @RequestMapping("/index")
        public String index() {
            return "index";
        }
        @RequestMapping("/")
        public String addTalk() {
            return "hello";
        }
        @RequestMapping("/text")
        public String addText() {
            return "text";
        }
        @RequestMapping("/upload")
        public String upload() {
            return "upload";
        }
        @RequestMapping("/queryData")
        public String queryData() {
            return "queryData";
        }
}
