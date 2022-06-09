package ru.yandex.practicum.catsgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.exceptions.UserNotFoundException;
import ru.yandex.practicum.catsgram.model.Post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class PostService {
    private final UserService userService;
    private final List<Post> posts = new ArrayList<>();

    @Autowired
    public PostService(UserService userService) {
        this.userService = userService;
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post create(Post post) {
        if (userService.findUserByEmail(post.getAuthor()) != null) {
            posts.add(post);
            return post;
        } else {
            throw new UserNotFoundException("Пользователь " + post.getAuthor() + " не найден");
        }
    }

    public List<Post> findAll(Integer size, Integer from, String sort) {
        List<Post> subList = new ArrayList<>();
        int indexFrom = 0;
        if (sort.equals("asc")) {
            posts.sort(Comparator.comparing(Post::getCreationDate));
        } else {
            posts.sort(Comparator.comparing(Post::getCreationDate));
            posts.sort(Collections.reverseOrder());
        }

        for (Post post : posts) {
            if (post.getId().equals(from)) {
                indexFrom = posts.indexOf(post);
            }
        }
        for (int i = indexFrom; i <= size; i++) {
            subList.add(posts.get(i));
        }
        return subList;
    }
}