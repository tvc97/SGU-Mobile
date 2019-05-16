package android.binh.foodook.views.category

import android.binh.foodook.base.BasePresenter
import android.binh.foodook.base.BaseView

class SalePresenter : BasePresenter() {

    override fun getView(): SaleContract.View = mBaseView as SaleContract.View
}