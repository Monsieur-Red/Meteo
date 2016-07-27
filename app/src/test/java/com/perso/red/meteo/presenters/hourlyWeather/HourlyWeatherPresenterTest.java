package com.perso.red.meteo.presenters.hourlyWeather;

import com.perso.red.meteo.R;
import com.perso.red.meteo.models.weather.hourly.HourlyDataWeather;
import com.perso.red.meteo.views.hourlyWeather.HourlyWeatherView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by pierr on 27/07/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class HourlyWeatherPresenterTest {

    // Test Object
    private HourlyWeatherPresenter  presenter;

    // Mock Objects
    @Mock HourlyWeatherView         view;
    @Mock HourlyWeatherInteractor   interactor;
    @Mock List<HourlyDataWeather>   hourlyDataWeathers;

    @Before
    public void setUp() throws Exception {
        // Init Mock
        MockitoAnnotations.initMocks(this);

        // Init Test Object
        presenter = new HourlyWeatherPresenter((view));
    }

    @Test
    /* Not able to test it because the method tries to access the class GpsLocation which is not mocked. */
    public void testGetWeather() throws Exception {
        //presenter.getWeather(false);

        //verify(view).showProgress();
        //verify(interactor).getWeather();
    }

    @Test
    public void testOnDialog() throws Exception {
        presenter.onDialog(R.string.connection_problem_dialog_title, R.string.connection_problem_dialog_message);

        verify(view).hideProgress();
    }

    @Test
    public void testOnSuccessGetWeather() throws Exception {
        presenter.onSuccessGetWeather(hourlyDataWeathers);

        verify(view).hideProgress();
    }

}