<template>
	<view class="content">
		<view v-if="!hasLogin" class="userinfo">
			<view class="btn-row">
				<button type="default" @tap="bindLogin">登录</button>
			</view>
		</view>
		<view v-else class="userinfo">
			<image :src="userInfo.avatarUrl" class="userinfo-avatar"></image>
			<view class="userinfo-nickname">姓名：{{userInfo.nickName}}</view>
			<view v-if="userInfo.gender==0" class="userinfo-nickname">性别：未知</view>
			<view v-if="userInfo.gender==1" class="userinfo-nickname">性别：男</view>
			<view v-if="userInfo.gender==2" class="userinfo-nickname">性别：女</view>
			<view class="btn-row">
				<button type="default" @tap="bindLogout">退出</button>
			</view>
		</view>
	</view>
</template>

<script>
	import {mapState,mapActions} from 'vuex';
	export default {
		computed: {
			...mapState(['userInfo','hasLogin']),
		},
		data() {
			return {
				title: 'Hello'
			}
		},
		onLoad() {

		},
		methods: {
			...mapActions(['logout']),
			bindLogin() {
				uni.reLaunch({url: '/pages/index/login'});
			},
			bindLogout() {
				this.$store.dispatch('logout').then(() => {
					uni.reLaunch({url: '/pages/index/index'});
				}).catch((error) => {
					if (error !== 'error') {
						uni.showToast({title: error,icon: "none"});
					}
				})
			},
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
