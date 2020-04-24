package data.library;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.SavedTrack;
import com.wrapper.spotify.requests.data.library.GetUsersSavedTracksRequest;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetUsersSavedTracksExample {
  private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";

  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
          .setAccessToken(accessToken)
          .build();
  private static final GetUsersSavedTracksRequest getUsersSavedTracksRequest = spotifyApi.getUsersSavedTracks()
//          .limit(10)
//          .offset(0)
//          .market(CountryCode.SE)
          .build();

  public static void getUsersSavedTracks_Sync() {
    try {
      final Paging<SavedTrack> savedTrackPaging = getUsersSavedTracksRequest.execute();

      System.out.println("Total: " + savedTrackPaging.getTotal());
    } catch (IOException | SpotifyWebApiException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public static void getUsersSavedTracks_Async() {
    try {
      final CompletableFuture<Paging<SavedTrack>> pagingFuture = getUsersSavedTracksRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final Paging<SavedTrack> savedTrackPaging = pagingFuture.join();

      System.out.println("Total: " + savedTrackPaging.getTotal());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }
}