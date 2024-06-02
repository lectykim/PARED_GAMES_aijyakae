package com.paredgames.aijyakae.data.billing

import android.content.Context
import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.AcknowledgePurchaseResponseListener
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.Purchase.PurchaseState
import com.android.billingclient.api.PurchasesResponseListener
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams
import com.paredgames.aijyakae.MainActivity
import com.paredgames.aijyakae.data.util.SharedPreferenceDataKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillingManager(
    private val context:Context,
    private val mainActivity: MainActivity
) {
    private var sharedPreferences = context.getSharedPreferences(SharedPreferenceDataKeys.SHARED_KEY_BEFORE_LOGIN,Context.MODE_PRIVATE)

    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
                for (purchase in purchases) {
                    handlePurchase(purchase)
                }
            } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
                // 사용자가 결제를 취소했습니다.
                Log.d("Billing", "User canceled the purchase")
            }else if(billingResult.responseCode == BillingClient.BillingResponseCode.DEVELOPER_ERROR){
                if (purchases != null) {
                    for (purchase in purchases) {
                        handlePurchase(purchase)
                    }
                }
            }
            else {
                // 다른 결제 오류 처리
                Log.d("Billing", "Purchase failed with response code: ${billingResult.responseCode}")
            }

        }

    private var billingClient = BillingClient.newBuilder(context)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    private val immutableList = mutableListOf(
        QueryProductDetailsParams.Product.newBuilder()
            .setProductId("aijyakae_remove_ad")
            .setProductType(BillingClient.ProductType.SUBS)
            .build()
    )



    private val acknowledgePurchaseResponseListener = AcknowledgePurchaseResponseListener{

    }

    private val purchasesResponseListener = PurchasesResponseListener{ billingResult, purchases ->
        var check = false
        if(billingResult.responseCode == BillingClient.BillingResponseCode.OK){
            Log.d("purchase plan count",purchases.size.toString())
            for(purchase in purchases){
                Log.d("purchase info","${purchase.orderId} + ${purchase.isAcknowledged} + ${purchase.purchaseState}")

                if(purchase.purchaseState==PurchaseState.PURCHASED&&purchase.isAcknowledged){
                    val editor = sharedPreferences.edit()
                    editor.putString("isPurchased","true")
                    editor.apply()
                    check = true
                }
            }

            if(!check){
                val editor = sharedPreferences.edit()
                editor.putString("isPurchased","false")
                editor.apply()
            }
        }
    }





    private val queryProductDetailsParams =
        QueryProductDetailsParams.newBuilder()
            .setProductList(
                immutableList
            )
            .build()


    private fun handlePurchase(purchase: Purchase) {
        // Purchase retrieved from BillingClient#queryPurchasesAsync or your PurchasesUpdatedListener.

        // Verify the purchase.
        // Ensure entitlement was not already granted for this purchaseToken.
        // Grant entitlement to the user.

        if(purchase.purchaseState == PurchaseState.PURCHASED){
            if(!purchase.isAcknowledged){
                val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
                    .setPurchaseToken(purchase.purchaseToken)
                billingClient.acknowledgePurchase(acknowledgePurchaseParams.build(),acknowledgePurchaseResponseListener)
            }
        }
    }



    fun startBilling(){
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if(billingResult.responseCode == BillingClient.BillingResponseCode.OK){
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

                        //billingClient.acknowledgePurchase()

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

    fun verifyBilling(){

        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                val params = QueryPurchasesParams.newBuilder()
                    .setProductType(BillingClient.ProductType.SUBS)

                // uses queryPurchasesAsync Kotlin extension function
                billingClient.queryPurchasesAsync(params.build(),purchasesResponseListener)

                // check purchasesResult.billingResult
                // process returned purchasesResult.purchasesList, e.g. display the plans user owns
            }
            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })




    }
}