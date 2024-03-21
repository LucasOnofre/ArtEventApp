//package com.onoffrice.exchange.ui.detail
//
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.hasText
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.performClick
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.onoffrice.domain.interactors.GetArtworkDetail
//import com.onoffrice.domain.model.ExchangeDetail
//import com.onoffrice.domain.utils.DefaultDispatchers
//import com.onoffrice.domain.utils.DispatcherProvider
//import com.onoffrice.domain.utils.ResultWrapper
//import com.onoffrice.exchange.di.artEventModule
//import com.onoffrice.exchange.ui.utils.exchangeDetailMock
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.flow
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
//class ExchangeDetailScreenTest : KoinTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    private var mockInteractor = MockInteractor()
//
//    @get:Rule
//    val koinTestRule = KoinTestRule.create {
//        modules(
//            module {
//                factory<DispatcherProvider> { DefaultDispatchers() }
//                single { artEventModule }
//                viewModel { ArtEventDetailViewModel(mockInteractor, get()) }
//            })
//    }
//
//    @Before
//    fun setup() {
//        composeTestRule.setContent {
//            ArtEventDetailScreen(navController = null, "ExchangeArgsMock")
//        }
//    }
//
//    @Test
//    fun checkIfResponseFromInteractorIsDisplayedOnScreen() {
//        composeTestRule.onNode(hasText(exchangeDetailMock.name), useUnmergedTree = true)
//            .assertIsDisplayed()
//            .performClick()
//    }
//}
//
//class MockInteractor : GetArtworkDetail {
//    override suspend fun execute(exchangeId: String): Flow<ResultWrapper<List<ExchangeDetail>>> {
//        return flow {
//            emit(
//                ResultWrapper.Success(listOf(exchangeDetailMock))
//            )
//        }
//    }
//}