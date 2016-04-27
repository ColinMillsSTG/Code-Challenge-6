package com.codechallenges.service;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by colin.mills on 4/25/2016.
 *
 * Service for handling different data requests against a given set of presents and a wishlist.
 */

public interface PresentService {
    ArrayList<String> guessPresents(String wishlist, String presents);
    ArrayList<String> guessPresents(ArrayList<Map<String, String>> wishlist, ArrayList<Map<String, String>> presents);
}
