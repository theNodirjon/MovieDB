package com.exemple.movie.view.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exemple.movie.R
import com.exemple.movie.adapter.home.main.MainAdapter
import com.exemple.movie.databinding.HomeFragmentBinding
import com.exemple.movie.model.HomeListData.MainListData
import com.exemple.movie.model.response.base.BaseType
import com.exemple.movie.model.response.home.popular.Result
import com.exemple.movie.presenter.interfaces.MainPresenter
import com.exemple.movie.presenter.impl.PresenterImp
import com.exemple.movie.view.base.BaseFragment

class HomeFragment:BaseFragment(), MainPresenter.View {

    private lateinit var binding: HomeFragmentBinding

    private val mainAdapter = MainAdapter()

    private var presenter: MainPresenter.Presenter? = null

    override fun onFragmentReady() {


//        Toast.makeText(requireActivity(), "Ok", Toast.LENGTH_SHORT).show()


        presenter = PresenterImp(this)

        binding.mainList.adapter = mainAdapter
        binding.mainList.layoutManager = LinearLayoutManager(requireActivity(),LinearLayoutManager.VERTICAL,true)

        presenter?.loadData()
//        Log.d( "nmadir", "Response-> ${presenter.toString()}")

        binding.search.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment()
            findNavController().navigate(action)
        }

        mainAdapter.allButtonClick = {
            val action = HomeFragmentDirections.actionHomeFragmentToAllMoviesFragment()
            action.arguments.putString("movieType",it)
            findNavController().navigate(action)
        }
        binding.buttonSeting.setOnClickListener{
            findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToSettingFragment())
        }

        mainAdapter.onMainListClick = object :MainAdapter.OnMainListClick{
            override fun onMainClick(movie_id: Int) {
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment()
                action.arguments.putInt("movie_id",movie_id)
                findNavController().navigate(action)
            }

        }
    }

    override fun getLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onFragmentCreated() {

    }

    override fun onFragmentClosed() {
//        Toast.makeText(requireActivity(), "clouse", Toast.LENGTH_LONG).show()
        presenter?.cancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun dataState(isLoading: Boolean) {
        TODO("Not yet implemented")
    }

    override fun showPopularData(popularData: List<Result>) {
        mainAdapter.data.add(MainListData(getString(R.string.popularr),popularData as List<BaseType>))
    }

    override fun showTopRatedData(topRatedData: List<com.exemple.movie.model.response.home.topRated.Result>) {
        mainAdapter.data.add(MainListData(getString(R.string.top_rated),topRatedData as List<BaseType>))
    }

    override fun showNowPlayingData(nowPlayingData: List<com.exemple.movie.model.response.home.nowPlaying.Result>) {
        mainAdapter.data.add(MainListData(getString(R.string.now_playing),nowPlayingData as List<BaseType>))
    }

    override fun showUpcoming(upcomingData: List<com.exemple.movie.model.response.home.upcoming.Result>) {
        mainAdapter.data.add(MainListData(getString(R.string.upcoming),upcomingData as List<BaseType>))
    }

    override fun showError(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }


}