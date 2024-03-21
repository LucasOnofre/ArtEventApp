//package com.onoffrice.exchange.ui.list
//
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.hasText
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onAllNodesWithText
//import androidx.compose.ui.test.performClick
//import androidx.navigation.compose.ComposeNavigator
//import androidx.navigation.testing.TestNavHostController
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.onoffrice.domain.interactors.GetArtworks
//import com.onoffrice.domain.model.ArtEvent
//import com.onoffrice.domain.utils.DefaultDispatchers
//import com.onoffrice.domain.utils.DispatcherProvider
//import com.onoffrice.domain.utils.ResultWrapper
//import com.onoffrice.exchange.di.artEventModule
//import com.onoffrice.exchange.ui.ArtEventAppNavHost
//import com.onoffrice.exchange.ui.ARTWORK_DETAIL_ROUTE_ARG
//import com.onoffrice.exchange.ui.Routes
//import com.onoffrice.exchange.ui.utils.artEventMock
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.koin.androidx.viewmodel.dsl.viewModel
//import org.koin.dsl.module
//import org.koin.test.KoinTest
//import org.koin.test.KoinTestRule
//
//@RunWith(AndroidJUnit4::class)
//class ExchangeListScreenTest : KoinTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    private var mockInteractor = MockInteractor()
//
//    lateinit var navController: TestNavHostController
//
//    @get:Rule
//    val koinTestRule = KoinTestRule.create {
//        modules(
//            module {
//                factory<DispatcherProvider> { DefaultDispatchers() }
//                single { artEventModule }
//                viewModel { ArtworkListViewModel(mockInteractor, get()) }
//            })
//    }
//
//    @Before
//    fun setup() {
//        composeTestRule.setContent {
//            navController = TestNavHostController(LocalContext.current)
//            navController.navigatorProvider.addNavigator(ComposeNavigator())
//            ArtEventAppNavHost(navController = navController)
//            ArtworkListScreen(navController = navController)
//        }
//    }
//
//    @Test
//    fun checkIfResponseFromInteractorIsDisplayedOnScreen() {
//        composeTestRule.onNode(hasText(artEventMock.name), useUnmergedTree = true)
//            .assertIsDisplayed()
//    }
//
//    @Test
//    fun checkItemClickNavigationWithArgsToDetailScreen() {
//        composeTestRule.onAllNodesWithText(artEventMock.exchangeId)[0]
//            .performClick()
//
//        val route = navController.currentBackStackEntry?.destination?.route
//        val param = navController.currentBackStackEntry?.arguments?.getString(ARTWORK_DETAIL_ROUTE_ARG)
//
//        assertEquals(param, artEventMock.exchangeId)
//        assertEquals(route, "${Routes.ArtworkDetail.rout}{$ARTWORK_DETAIL_ROUTE_ARG}")
//    }
//}
//
//class MockInteractor: GetArtworks {
//    override suspend fun execute(): Flow<ResultWrapper<List<ArtEvent>>> {
//        return flow { emit(
//            ResultWrapper.Success(listOf(artEventMock))
//        ) }
//    }
//}