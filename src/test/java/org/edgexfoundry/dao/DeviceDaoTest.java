/*******************************************************************************
 * Copyright 2016-2017 Dell Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * @microservice: support-logging
 * @author: Jim White, Dell
 * @version: 1.0.0
 *******************************************************************************/

package org.edgexfoundry.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import org.edgexfoundry.domain.meta.Device;
import org.edgexfoundry.test.category.RequiresNone;
import org.edgexfoundry.test.data.DeviceData;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@Category(RequiresNone.class)
public class DeviceDaoTest {

  private static final String TEST_ID = "123";

  @InjectMocks
  private DeviceDao dao;

  @Mock
  private DeviceRepository repos;

  private Device device;

  @Before
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
    device = DeviceData.newTestInstance();
  }

  @Test
  public void testGetByIdOrNameWithNull() {
    assertNull("Returned device is not null", dao.getByIdOrName(null));
  }

  @Test
  public void testGetByIdOrName() {
    when(repos.findOne(TEST_ID)).thenReturn(device);
    device.setId(TEST_ID);
    assertEquals("Returned device is not expected", device, dao.getByIdOrName(device));
  }

  @Test
  public void testGetByIdOrNameWithNoId() {
    when(repos.findByName(DeviceData.TEST_NAME)).thenReturn(device);
    assertEquals("Returned device is not expected", device, dao.getByIdOrName(device));
  }
}
