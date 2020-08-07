import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);//在创建Vue实例之前

export default new Vuex.Store({
    state: {
        //存放组件之间共享的数据
        userInfo: null,
        hasLogin: null,
    },
	actions: {
	    // 退出
	    logout({commit, state}) {
	        return new Promise((resolve, reject) => {
				uni.request({
					url: 'http://localhost:8081/loginout',
					method: 'GET',
					header: {
						'content-type': 'application/x-www-form-urlencoded',
					},
					success: function(res) {
					  state.userInfo=null
					  state.hasLogin=null
					  uni.reLaunch({url: '/pages/index/index'});
					},
					fail: function(error) {
						console.log(error);
					}
				})
	        })
	    },
	},
})