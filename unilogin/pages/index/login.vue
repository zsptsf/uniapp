<template>
    <view class="container">
		<!-- 登录后使用open-data -->
		<view class="userinfo">
			<!-- 登录组件 https://developers.weixin.qq.com/miniprogram/dev/api/wx.getUserInfo.html -->
			<button v-if="!hasUserInfo" open-type="getUserInfo" @getuserinfo="onGetUserInfo(userInfo)">授权登录</button>
			<block v-else>
				<image :src="userInfo.avatarUrl" class="userinfo-avatar"/>
				<open-data class="userinfo-nickname" type="userNickName"></open-data>
			</block>
		</view>
    </view>
</template>

<script>
	export default {
		data(){
			return{
				hasUserInfo: true,//是否授权登录
				userInfo: null
			}
		  },
		
		  onLoad() {
		    // 页面加载时使用用户授权逻辑，弹出确认的框  
		    this.userAuthorized()
		  },
		  methods: {
			  userAuthorized() {
				  let that = this;
				// 查看是否授权
			    uni.getSetting({
			      success: data => {
					  //判断是否授权
			        if (data.authSetting['scope.userInfo']) {
			          uni.getUserInfo({
			            success: data => {
			                that.hasUserInfo = true,
			                that.userInfo = data.userInfo,
							that.onGetUserInfo(data.userInfo)
			            }
			          })
			        } else {//null或者是false,表示没有授权，显示授权按钮
			          that.hasUserInfo=false
			        }
			      },
				  fail: function(error) {
					console.log(error);
				  }
			    })
			  },
			  		
			  onGetUserInfo(userInfo) {
				 let that = this;
			    if (userInfo) {
			      //小程序通过uni.login()获取code
			      uni.login({
			        success: function(login_res) {
			          //获取用户信息
			          uni.getUserInfo({
			            success: function(info_res) {
			              // 小程序通过uni.request()发送code到开发者服务器
						  uni.request({
						  url: 'http://localhost:8081/login',
						  method: 'POST',
						  header: {
							'content-type': 'application/x-www-form-urlencoded',
						  },
						  data: {
							code: login_res.code, //临时登录凭证
							rawData: info_res.rawData, //用户非敏感信息
							signature: info_res.signature, //签名
							encrypteData: info_res.encryptedData, //用户敏感信息
							iv: info_res.iv //解密算法的向量
						  },
						  success: function(res) {
							  // 全局存储
							  that.$store.state.userInfo=userInfo
							  that.$store.state.hasLogin=true
							  uni.reLaunch({url: '/pages/index/index'});
						  },
						  fail: function(error) {
							//调用服务端登录接口失败
							console.log(error);
						  }
						})
						that.hasUserInfo= true,
						that.userInfo = userInfo
			            },
						fail: function(error) {
							//用户拒绝授权
							uni.reLaunch({url: '/pages/index/index'});
						}
			          })
			        }
			      })
			    }else{//第一次授权登录
					uni.login({
						success: function(login_res) {
						  //获取用户信息
						  uni.getUserInfo({
							success: function(info_res) {
							  // 2. 小程序通过wx.request()发送code到开发者服务器
							  uni.request({
							  url: 'http://localhost:8081/login',
							  method: 'POST',
							  header: {
								'content-type': 'application/x-www-form-urlencoded',
							  },
							  data: {
								code: login_res.code, //临时登录凭证
								rawData: info_res.rawData, //用户非敏感信息
								signature: info_res.signature, //签名
								encrypteData: info_res.encryptedData, //用户敏感信息
								iv: info_res.iv //解密算法的向量
							  },
							  success: function(res) {
								  that.$store.state.userInfo=info_res.userInfo
								  that.$store.state.hasLogin=true
								  uni.reLaunch({url: '/pages/index/index'});
							  },
							  fail: function(error) {
								//调用服务端登录接口失败
								console.log(error);
							  }
							})
							that.userInfo = info_res.userInfo
							that.hasUserInfo= true
						},
						fail: function(error) {
							//用户拒绝授权
							uni.reLaunch({url: '/pages/index/index'});
						}
					  })
					}
				  })
				}
			}
		}
	}
</script>

<style>
.container {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  padding: 200rpx 0;
  box-sizing: border-box;
} 
.userinfo {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.userinfo-avatar {
  width: 128rpx;
  height: 128rpx;
  margin: 20rpx;
  border-radius: 50%;
}

.userinfo-nickname {
  color: #aaa;
}

</style>
