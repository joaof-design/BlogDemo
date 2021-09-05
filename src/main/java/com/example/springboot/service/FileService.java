package com.example.springboot.service;

import com.example.springboot.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Value("${share.dir}")
    private String path;

    public List<Post> parseCSVFile(String filename) throws IOException {
        final List<Post> posts = new ArrayList<>();
        File file = new File(path + "\\" + filename);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line = "";
        while ((line = br.readLine()) != null) {
            String[] data = line.split(";");
            posts.add(Post.builder().title(data[0]).description(data[1]).content(data[2]).build());
        }

        return posts;
    }
}
