package com.tiantiannews.base;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by guqian on 2017/5/24.
 */

public class CheckPermissionsActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        PermissionsGranted(requestCode, perms);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            PermissionsDenied(requestCode, perms);
        } else {

        }
    }

    protected void PermissionsGranted(int requestCode, List<String> perms) {

    }

    protected void PermissionsDenied(int requestCode, List<String> perms) {

    }
}
