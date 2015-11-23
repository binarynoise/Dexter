/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.dexter;

import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * An in detail report of the request permission process.
 */
public final class PermissionsReport {

  private final List<PermissionGrantedResponse> grantedPermissionResponses;
  private final List<PermissionDeniedResponse> deniedPermissionResponses;

  PermissionsReport() {
    grantedPermissionResponses = new LinkedList<>();
    deniedPermissionResponses = new LinkedList<>();
  }

  boolean addGrantedPermissionResponse(PermissionGrantedResponse response) {
    return grantedPermissionResponses.add(response);
  }

  boolean addDeniedPermissionResponse(PermissionDeniedResponse response) {
    return deniedPermissionResponses.add(response);
  }

  /**
   * Returns a collection with all the permissions that has been granted
   */
  public Collection<PermissionGrantedResponse> getGrantedPermissionResponses() {
    return grantedPermissionResponses;
  }

  /**
   * Returns a collection with all the permissions that has been denied
   */
  public Collection<PermissionDeniedResponse> getDeniedPermissionResponses() {
    return deniedPermissionResponses;
  }

  /**
   * Returns whether the user has granted all the requested permission
   */
  public boolean hasUserGrantedAllPermissions() {
    return deniedPermissionResponses.isEmpty();
  }

  /**
   * Returns whether the user has permanently denied any of the requested permissions
   */
  public boolean hasPermanentlyDeniedAnyPermission() {
    boolean hasPermanentlyDeniedAnyPermission = false;

    for (PermissionDeniedResponse deniedResponse : deniedPermissionResponses) {
      if (deniedResponse.isPermanentlyDenied()) {
        hasPermanentlyDeniedAnyPermission = true;
        break;
      }
    }

    return hasPermanentlyDeniedAnyPermission;
  }
}
