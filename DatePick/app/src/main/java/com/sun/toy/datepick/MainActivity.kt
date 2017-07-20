package com.sun.toy.datepick

import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.sun.toy.datepick.models.Group
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var edtInput: TextInputEditText? = null
    private var btnSearch: TextView? = null
    private var frgEventList: FrgEventList? = null
    private var frgDetail: FrgDetail? = null
    private var container: View? = null
    private var edtSearch: TextInputEditText? = null
    private var btnInput: TextView? = null
    private var ctSearch: View? = null
    var auth: FirebaseAuth? = null

    var mRealm: Realm by Delegates.notNull<Realm>()
    var mRealmConfig: RealmConfiguration by Delegates.notNull<RealmConfiguration>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtInput = findViewById(R.id.edt_input) as TextInputEditText
        btnSearch = findViewById(R.id.btn_search) as TextView
        edtSearch = findViewById(R.id.edt_search) as TextInputEditText
        btnInput = findViewById(R.id.btn_input) as TextView
        ctSearch = findViewById(R.id.ct_search) as View
        container = findViewById(R.id.container)

        edtInput?.setOnClickListener(this)
        btnSearch?.setOnClickListener(this)
        btnInput?.setOnClickListener(this)
        edtSearch?.setOnClickListener(this)
        ctSearch?.setOnClickListener(this)

        FirebaseApp.initializeApp(baseContext)
        auth = FirebaseAuth.getInstance()
//        auth?.apply {
//            if (currentUser == null) {
//                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
//                        .setAvailableProviders(
//                                Arrays.asList(AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
//                                        AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build(),
//                                        AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build())).build(), 123)
//            }
//        }
        initRealm()
        loadChannel()
    }

    fun initRealm() {
        mRealmConfig = RealmConfiguration.Builder().build()
        mRealm = Realm.getInstance(mRealmConfig)
        Realm.deleteRealm(mRealmConfig)

    }

    private fun loadChannel() {
        // TODO 먼저 서버 검사
        // TODO 그다음 디비 검사
        mRealm.beginTransaction()
        val result = mRealm.where(Group::class.java).findAll() as RealmResults<Group>

        for (i in 0 until result.size) {
            Log.d("name", result.get(i).getName());
        }
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.btn_search) {
            if (TextUtils.isEmpty(edtSearch?.text)) {
                Toast.makeText(this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                if (edtSearch != null) {
                    var tmp = edtSearch?.text.toString()
                    if (tmp.equals("a")) {
                        ctSearch?.visibility = View.VISIBLE
                        var txtName = ctSearch?.findViewById(R.id.txt_name) as TextView
                        txtName.text = "aaasldfjasldfj"
                    } else {
                        Toast.makeText(this, "찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } else if (v?.id == R.id.btn_input) {
            // TODO firebase
        } else if (v?.id == R.id.ct_search) {
            addDetailFragment();
        }
    }

    private fun addDetailFragment() {
        frgDetail = FrgDetail.newInstance("name") as FrgDetail
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, frgDetail, FrgDetail.javaClass.simpleName).commit()
        }
    }

    private fun addFragment() {
        frgEventList = FrgEventList.newInstance()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, frgEventList, FrgEventList.javaClass.simpleName).commit()
        }
    }

    override fun onBackPressed() {
        supportFragmentManager.apply {
            if (findFragmentByTag(FrgDetail.javaClass.simpleName) != null) {
                beginTransaction().remove(frgDetail).commit()
            } else if (findFragmentByTag(FrgEventList.javaClass.simpleName) != null) {
                beginTransaction().remove(frgEventList).commit()
            } else {
                super.onBackPressed()
            }
        }
    }
}
