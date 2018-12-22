package com.example.ProjekatIsa.service;
import com.example.ProjekatIsa.model.*;

public interface CurrentUserService {

	boolean canAccessUser(CurrentUser currentUser, Long userId);
}
