package com.paredgames.aijyakae.data.billing

import android.content.Context
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.paredgames.aijyakae.MainActivity

class BillingManager(
    private val context:Context,
    private val mainActivity: MainActivity
) {

    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            // To be implemented in a later section.
        }

    private var billingClient = BillingClient.newBuilder(context)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    private val immutableList = mutableListOf(
        QueryProductDetailsParams.Product.newBuilder()
            .setProductId("pared_gaems_remove_ad")
            .setProductType(BillingClient.ProductType.SUBS)
            .build()
    )



    val queryProductDetailsParams =
        QueryProductDetailsParams.newBuilder()
            .setProductList(
                immutableList
            )
            .build()




    fun startBilling(){

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                    // The BillingClient is ready. You can query purchases here.

                    billingClient.queryProductDetailsAsync(queryProductDetailsParams) {
                            billingResult,
                            productDetailsList ->

                        val productDetailsParamsList = mutableListOf<BillingFlowParams.ProductDetailsParams>()

                        productDetailsList.forEach{
                            productDetailsParamsList.add(
                                BillingFlowParams.ProductDetailsParams.newBuilder()
                                    .setProductDetails(it)
                                    .setOfferToken(it.subscriptionOfferDetails!![0].offerToken)
                                    .build()
                            )
                        }

                        val billingFlowParams = BillingFlowParams.newBuilder()
                            .setProductDetailsParamsList(productDetailsParamsList)
                            .build()

                        // Launch the billing flow
                        val billingRes = billingClient.launchBillingFlow(mainActivity, billingFlowParams)

                    }

                }
            }
            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })


    }
}