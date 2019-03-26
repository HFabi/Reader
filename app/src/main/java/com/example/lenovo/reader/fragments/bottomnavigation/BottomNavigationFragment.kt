package com.example.lenovo.reader.fragments.bottomnavigation

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lenovo.reader.R
import com.example.lenovo.reader.navigation.Router
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_bottomsheet.dashboard_navigationview
import javax.inject.Inject

class BottomNavigationFragment @Inject constructor() : BottomSheetDialogFragment() {

  @Inject
  lateinit var router: Router

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_bottomsheet, container, false)
  }

  override fun onCancel(dialog: DialogInterface) {
    super.onCancel(dialog)
  }

  override fun onDismiss(dialog: DialogInterface) {
    super.onDismiss(dialog)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    dashboard_navigationview.setNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.action_bottom_settings -> {
          router.goToSettings(this)
          dismiss()
        }
        R.id.action_bottom_about -> {
          router.goToAbout(this)
          dismiss()
        }
      }
      true
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    AndroidSupportInjection.inject(this)
  }
}