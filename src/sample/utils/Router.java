package sample.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Router {

    public void loadPrevPage()
    {
        if (Constants.current_url==Page.LANDING_PAGE)
        {

        }
        else if (Constants.last_url==Page.WEB_VIEW)
        {

        }
    }

}
